FUNCTION script_place_demon_lair();
PRIVATE
    int result;
BEGIN
    game.state = STATE_DIALOG_ID;

    if (quests.main == STATUS_DEMON_KING)
        state_dialog_putline("You enter the demon king's lair. This is it...", 400);
        state_dialog_ask("Are you ready for the final boss?");
        if(dialog.chosen_answer == 1)
            result = state_battle_start("Demon King", 6);
            if (result)
                script_place_demon_lair_end_text();
            end
        else 
            state_dialog_putline("Yeah, let's get back later...", 400);        
        end
        
    else
        state_dialog_putline("Nothing to see here.", 400);
    end

    state_dialog_waitkey();
    game.state = STATE_MAP_ID;
END


FUNCTION script_place_demon_lair_end_text()

BEGIN
    sound_play(snd.win);
    state_dialog_putline("The Demon King shakes with violence as he starts to", 400);
    state_dialog_putline("fall apart. ''This cannot be!'' he says as it happens.", 400);
    state_dialog_putline("The crystals start to shine inside his chest, right before", 400);
    state_dialog_putline("he explodes into a million of tiny particles of blood.", 400);
    state_dialog_putline("", 400);

    state_dialog_putline("You have won. But there's no glory waiting for you.", 400);
    state_dialog_putline("You exit the lair, and the guards are waiting for you.", 400);
    state_dialog_putline("[Guard Commander]: ''You are guilty of regicide, and thus", 400);
    state_dialog_putline("you are sentenced to death. Get him!'' says the fanciest", 400);
    state_dialog_putline("looking one.", 400);
    state_dialog_putline("", 400);

    state_dialog_putline("You are taken to the prison, awaiting your inevitable end.", 400);
    state_dialog_waitkey();

    if (inventory.weddingRing)
        script_place_demon_lair_wedding_text();
    elseif (quests.saveMerchant == STATUS_ENDED)
        script_place_demon_lair_merchant_text();
    end

    state_dialog_putline("The day comes and you are finally executec.", 400);
    state_dialog_putline("That's it. - THE END -", 400);
    state_dialog_waitkey();

    exit(0,0);
END


FUNCTION script_place_demon_lair_wedding_text()

BEGIN
    state_dialog_putline("Someone arrives to your cell in the middle of the night.", 400);
    state_dialog_putline("It's the inn keeper, whose sex we won't mention so it", 400);
    state_dialog_putline("can fit whatever you want without having to write more!", 400);
    state_dialog_putline("[Inn Keeper]: ''Hey.. I heard you were here. I just...", 400);
    state_dialog_putline("I just wanted to ask... I saw you bought that ring...", 400);
    state_dialog_putline("Did you buy it for me? Shall we marry and escape?''", 400);
    state_dialog_ask("Do you marry the inn keeper?");
    if(dialog.chosen_answer == 1)
        state_dialog_putline("You two escape and marry shortly after.");
        state_dialog_putline("You live a long and happy life. Yay!");
        state_dialog_putline("- THE END -");
        state_dialog_waitkey();
        exit(0,0);
    else 
        state_dialog_putline("");
        state_dialog_putline("Well... this is awkward... I better leave.", 400);        
    end
    state_dialog_putline("", 400);
END




FUNCTION script_place_demon_lair_merchant_text()

BEGIN
    state_dialog_putline("Someone arrives to your cell in the middle of the night.", 400);
    state_dialog_putline("It's the merchant, still grateful for being saved before.", 400);
    state_dialog_putline("[Merchant]: ''Hey.. I heard you were here. I just...", 400);
    state_dialog_putline("guess it's time to return the favor. I can get you out", 400);
    state_dialog_putline("of here, but we must be quick''", 400);
    state_dialog_ask("Do you accept his help?");
    if(dialog.chosen_answer == 1)
        state_dialog_putline("You escape.");
        state_dialog_putline("You start a new life in anither city and are never caught.");
        state_dialog_putline("- THE END -");
        state_dialog_waitkey();
        exit(0,0);
    else 
        state_dialog_putline("");
        state_dialog_putline("Well... fine, I did what I had to do. Bye.", 400);        
    end
    state_dialog_putline("", 400);
END


