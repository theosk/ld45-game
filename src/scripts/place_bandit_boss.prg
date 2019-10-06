FUNCTION script_place_bandit_boss();

PRIVATE
    int result;

BEGIN
    game.state = STATE_DIALOG_ID;

    if (quests.main < STATUS_BANDITS_DEFEATED)
        state_dialog_putline("You are inside the bandits lair. Their boss is here.", 400);
        state_dialog_ask("Do you want to fight?");
        if(dialog.chosen_answer == 1)
            result = state_battle_start("bandit boss", 4);
            if (result)
                script_place_bandit_boss_end_text();
            end
        else 
            state_dialog_putline("");
            state_dialog_putline("Yeah, let's get out of here...", 400);        
        end
        
    else
        state_dialog_putline("The bandit boss is dead. You find nothing of value.", 400);
    end
    state_dialog_waitkey();
    game.state = STATE_MAP_ID;
END


FUNCTION script_place_bandit_boss_end_text()

BEGIN
    sound_play(snd.win);
    state_dialog_putline("The bandit boss is defeated. The other bandits will still", 400);
    state_dialog_putline("cause trouble, but the guards will be able to handle them.", 400);
    state_dialog_putline("", 400);

    state_dialog_putline("You should go back to the castle.", 400);

    quests.main = STATUS_BANDITS_DEFEATED;
    map.area_unlocked[LOCATION_CASTLE] = 1;
END