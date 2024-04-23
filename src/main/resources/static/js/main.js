'use strict';

const announcePage = document.querySelector('#announce-page')
const chatPage = document.querySelector('#chat-page');
const announceForm = document.querySelector('#announceForm')
const messageForm = document.querySelector('#messageForm');
const messageInput = document.querySelector('#message');
const connectingElement = document.querySelector('.connecting');
const chatArea = document.querySelector('#chat-messages');
const logout = document.querySelector('#logout');

let token = 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIrOTk4OTE0MDczNDM4IiwiaWF0IjoxNzEzNjkxNzA4LCJleHAiOjE3MTQyOTY1MDh9.WsYdHCbJdgh1bNU2xMPSQqdHdQISxH1qYovdpkEL8mY'

let stompClient = null;
let announceId = null;
let userId = null;
let selectedUserId = null;
let clickedUserId = null;
let chatRoom = null;

function connect(event) {
    announceId = parseInt(document.querySelector('#announceId').value.trim());
    selectedUserId = parseInt(document.querySelector('#userId').value.trim())

    createOrGetChatRoom(announceId).then(data => {
        chatRoom = data;
    })

    if (announceId && chatRoom) {
        announcePage.classList.add('hidden');
        chatPage.classList.remove('hidden');
        const socket = new SockJS('/ws');
        stompClient = Stomp.over(socket);

        stompClient.connect({}, onConnected, onError);
    }
    event.preventDefault();
}


function onConnected() {
    // register the connected user
    let connectedUserId = parseInt(document.querySelector('#userId').value.trim());
    stompClient.send("/app/user.addUser",
        {},
        JSON.stringify({user: {id : connectedUserId}, chatRoom: chatRoom, chatStatus: 'ONLINE'})
    );


    getChatUser(chatRoom.id, connectedUserId).then(data => {
        selectedUserId = data.id
        stompClient.subscribe(`/user/public`, onMessageReceived());
        stompClient.subscribe(`/user/${selectedUserId}/queue/messages`, onMessageReceived);

        document.querySelector('#connected-user-fullname').textContent = selectedUserId;
        findAndDisplayConnectedUsers().then();
    })
}

async function onMessageReceived(payload) {

    await findAndDisplayConnectedUsers();
    if(payload) {
        const message = JSON.parse(payload.body);
        if (clickedUserId && message && clickedUserId === message.sender.id) {
            displayMessage(message.sender.id, message.content);
            chatArea.scrollTop = chatArea.scrollHeight;
        }

        if (clickedUserId) {
            document.querySelector(`#user${clickedUserId}`).classList.add('active');
        } else {
            messageForm.classList.add('hidden');
        }

        const notifiedUser = document.querySelector(`#user${message.sender.id}`);
        if (notifiedUser && !notifiedUser.classList.contains('active')) {
            const nbrMsg = notifiedUser.querySelector('.nbr-' +
                'msg');
            nbrMsg.classList.remove('hidden');
            nbrMsg.textContent = '';
        }
    }
}

async function createOrGetChatRoom(announceId) {
    const  response = await fetch('/api/v1/chatroom/create-or-get/' + announceId, {
        method : 'GET',
        headers : {
            'Content-type' : 'application/json',
            Authorization : `Bearer ${token}`
        }
    });

    return await response.json();
}


async function getChatUser(roomId, userId) {
    const  response = await fetch('/api/v1/chat_user/get/' + userId + '/' + roomId, {
        method : 'GET',
        headers : {
            'Content-type' : 'application/json',
            Authorization : `Bearer ${token}`
        }
    });

    return await response.json();
}

async function findAndDisplayConnectedUsers() {
    const connectedUsersResponse = await fetch('/api/v1/chat_user/online/' + chatRoom.id, {
        method : 'GET',
        headers : {
            'Content-type' : 'application/json',
            Authorization : `Bearer ${token}`
        }
    });
    let connectedUsers = await connectedUsersResponse.json();
    connectedUsers = connectedUsers.filter(chatUser => chatUser.id !== selectedUserId);
    const connectedUsersList = document.getElementById('connectedUsers');
    connectedUsersList.innerHTML = '';

    connectedUsers.forEach(user => {
        appendUserElement(user, connectedUsersList);
        if (connectedUsers.indexOf(user) < connectedUsers.length - 1) {
            const separator = document.createElement('li');
            separator.classList.add('separator');
            connectedUsersList.appendChild(separator);
        }
    });
}

function appendUserElement(chatUser, connectedUsersList) {
    const listItem = document.createElement('li');
    listItem.classList.add('user-item');
    listItem.id = 'user' + chatUser.id;

    const userImage = document.createElement('img');
    userImage.src = '../img/user_icon.png';

    const usernameSpan = document.createElement('span');
    usernameSpan.textContent = chatUser.user.firstname;

    const receivedMsgs = document.createElement('span');
    receivedMsgs.textContent = '0';
    receivedMsgs.classList.add('nbr-msg', 'hidden');

    listItem.appendChild(userImage);
    listItem.appendChild(usernameSpan);
    listItem.appendChild(receivedMsgs);

    listItem.addEventListener('click', userItemClick);

    connectedUsersList.appendChild(listItem);
}

function userItemClick(event) {
    document.querySelectorAll('.user-item').forEach(item => {
        item.classList.remove('active');
    });
    messageForm.classList.remove('hidden');

    const clickedUser = event.currentTarget;
    clickedUser.classList.add('active');

    clickedUserId = parseInt(clickedUser.getAttribute('id').substring('user'.length));
    fetchAndDisplayUserChat().then();

    const nbrMsg = clickedUser.querySelector('.nbr-msg');
    nbrMsg.classList.add('hidden');
    nbrMsg.textContent = '0';

}

function displayMessage(senderId, content) {
    const messageContainer = document.createElement('div');
    messageContainer.classList.add('message');
    if (senderId === selectedUserId) {
        messageContainer.classList.add('sender');
    } else {
        messageContainer.classList.add('receiver');
    }
    const message = document.createElement('p');
    message.textContent = content;
    messageContainer.appendChild(message);
    chatArea.appendChild(messageContainer);
}

async function fetchAndDisplayUserChat() {
    const userChatResponse = await fetch(`/messages/${selectedUserId}/${clickedUserId}`);
    const userChat = await userChatResponse.json();
    chatArea.innerHTML = '';
    userChat.forEach(chat => {
        displayMessage(chat.sender.id, chat.content);
    });
    chatArea.scrollTop = chatArea.scrollHeight;
}


function onError() {
    connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
    connectingElement.style.color = 'red';
}


function sendMessage(event) {
    const messageContent = messageInput.value.trim();
    if (messageContent && stompClient) {
        const chatMessage = {
            sender: {
                id: selectedUserId,
                chatRoom : chatRoom
            },
            recipient: {
                id: clickedUserId,
                chatRoom : chatRoom
            },
            content: messageContent
        };
        stompClient.send("/app/chat", {}, JSON.stringify(chatMessage));
        displayMessage(selectedUserId, messageInput.value.trim());
        messageInput.value = '';
    }
    chatArea.scrollTop = chatArea.scrollHeight;
    event.preventDefault();
}

// function onLogout() {
//     stompClient.send("/app/user.disconnectUser",
//         {},
//         JSON.stringify({id: selectedUserId, chatStatus: 'OFFLINE'})
//     );
//     window.location.reload();
// }

announceForm.addEventListener('submit', connect, true); // step 1
messageForm.addEventListener('submit', sendMessage, true);
// logout.addEventListener('click', onLogout, true);
// window.onbeforeunload = () => onLogout();
