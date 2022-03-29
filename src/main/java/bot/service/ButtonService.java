package bot.service;

import bot.constant.Event;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;


import java.util.ArrayList;
import java.util.List;


public class ButtonService{


    public ReplyKeyboardMarkup setButton(List<KeyboardRow> listButton){
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setKeyboard(listButton);
        return replyKeyboardMarkup;
    }

    public List<KeyboardRow> createButton(List<Event> buttons){
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.addAll(buttons.stream().map(e-> new KeyboardButton(e.getTitle())).toList());
        keyboardRows.add(keyboardRow);
        return keyboardRows;
    }
}
