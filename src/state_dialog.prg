PROCESS state_dialog()

PRIVATE hidden;

BEGIN
    while (game.state != STATE_DIALOG_ID) frame; end
    hidden = false;
    state_dialog_show();


    loop
        if(game.state != STATE_DIALOG_ID && !hidden)
            hidden = 1;
            state_dialog_hide();
        end
        
        if (hidden && game.state == STATE_DIALOG_ID)
            state_dialog_show();
            hidden = 0;
        end
        frame;
    end
END

FUNCTION state_dialog_putline(string text)

PRIVATE
    int wait_text_id;

BEGIN
    if (dialog.currentLine == 10)
        state_dialog_waitkey();
        state_dialog_clear();
    end

    // Add line
    dialog.line_text[dialog.currentLine] = text;
    dialog.line_id[dialog.currentLine] = write(game.font, dialog.text_offset_x, dialog.text_offset_y + dialog.currentLine * dialog.text_vertical_space, 0, text, 0);
    dialog.currentLine ++;
END

FUNCTION state_dialog_putline(string text, int delay)

BEGIN
    state_dialog_putline(text);
    if (!key(_s))
        for (x=0; x< delay; x++);
            if(key(_s) || mouse.right) frame; break; end
            frame(10);
        end
    end
END

FUNCTION state_dialog_waitkey()

BEGIN
    sound_play(snd.ask);
    x = write(game.font, 600, 460, 8, "Press Space to continue...");
    repeat frame; until(key(_space));
    sound_play(snd.select);
    repeat frame; until(!key(_space));
    delete_text(x);
END

FUNCTION state_dialog_clear()

BEGIN
    // 1. Clear all lines
    from x = 0 to 9;
        delete_text(dialog.line_id[x]);
        dialog.line_text[x] = "";
    end
    // 2. Set current line to 0
    dialog.currentLine = 0;
END


FUNCTION state_dialog_hide()

BEGIN
    state_dialog_clear();
    graph = img_bg;
    clear_screen();
    x = 320;
    y = 240;
    from size = 100 to 0; frame(20); end

END


FUNCTION state_dialog_show()

BEGIN
    graph = img_bg;
    clear_screen();
    x = 320;
    y = 240;
    from size = 0 to 100; frame(20); end

    put_screen(0, img_bg);
END

FUNCTION state_dialog_ask(string text)

PRIVATE
    int wait_text_id;

BEGIN
    text = text +  " [Y/N]";
    wait_text_id = write(game.font, dialog.text_offset_x, 460, 6, text);
    dialog.answered = 0;
    sound_play(snd.ask);
    repeat
        if (key(_y))
            dialog.answered = 1;
            dialog.chosen_answer = 1; 
        end
        if (key(_n))
            dialog.answered = 1;
            dialog.chosen_answer = 0; 
        end
        frame; 
    until(dialog.answered);
    sound_play(snd.select);
    repeat frame; until(!key(_y) && !key(_n));
    delete_text(wait_text_id);
    
END