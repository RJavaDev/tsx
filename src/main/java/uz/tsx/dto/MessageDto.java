package uz.tsx.dto;

public class MessageDto {
    private Integer fromUserId;
    private UserDto fromUser;

    private Integer toUserId;
    private UserDto toUser;

    private String text;

    private String[] filePaths;

    private boolean isRead;
}
