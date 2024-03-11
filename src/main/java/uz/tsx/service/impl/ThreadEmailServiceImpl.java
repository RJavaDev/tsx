package uz.tsx.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.tsx.entity.EmailEntity;
import uz.tsx.repository.EmailRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ThreadEmailServiceImpl extends Thread{
    private final EmailRepository repository;

    private final static long THREAD_SLEEP_TIME = 500000;
    public void  run(){
        try{
            Thread.sleep(THREAD_SLEEP_TIME);
            List<EmailEntity> all = repository.getAll();
            if (!all.isEmpty()){
                for (EmailEntity email:all){
                    repository.deleteById(email.getId());
                }
            }
        }catch(InterruptedException e){System.out.println(e);}

    }
}
