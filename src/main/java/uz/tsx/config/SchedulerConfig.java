//package uz.tsx.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//import uz.tsx.dto.UserResponse;
//import uz.tsx.service.ChatMessageService;
//
//@EnableScheduling
//@Configuration
//public class SchedulerConfig {
//
//    @Autowired
//    private SimpMessagingTemplate template;
//
//    private final ChatMessageService chatMessageService;
//
//    @Autowired
//    public SchedulerConfig(ChatMessageService chatMessageService) {
//        this.chatMessageService = chatMessageService;
//    }
//
//    @Scheduled(fixedDelay = 3000)
//    public void sendAdhocMessage() {
//        template.convertAndSend("/topic/user", new UserResponse("Fixed Delay Scheduler"));
//    }
//}
