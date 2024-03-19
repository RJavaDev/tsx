package uz.tsx.service.smsUtil.util;


import lombok.Data;
import uz.tsx.service.smsUtil.util.SMSConstant;

@Data
public class SMSModel {
    private String client_id;

    private Integer count;
    private String nik_id;
    private String phone;
    private String sending;
    private String text;


    public SMSModel(String phone, String text) {
        this.client_id = SMSConstant.CLIENT_ID;
        this.count = SMSConstant.COUNT;
        this.nik_id = SMSConstant.NIK_ID;
        this.sending = SMSConstant.SENDING;
        this.phone = phone;
        this.text = text;
    }


}



