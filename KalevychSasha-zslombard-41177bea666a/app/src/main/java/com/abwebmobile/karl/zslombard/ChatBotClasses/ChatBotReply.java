package com.abwebmobile.karl.zslombard.ChatBotClasses;

import java.util.List;

/**
 * Created by Karl on 01.03.2018.
 */

public class ChatBotReply {

    public ChatBotReply(String text, int self_code, int text_code, List<Integer> next_buttons) {
        this.text = text;
        this.self_code = self_code;
        this.text_code = text_code;
        this.next_buttons = next_buttons;
    }

   public String text;
   public int self_code;
   public int text_code;
   public   List<Integer> next_buttons;
}
