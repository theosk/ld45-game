FUNCTION script_place_bandit_camp();
PRIVATE
    int result;
BEGIN
    game.state = STATE_DIALOG_ID;
    state_dialog_putline("You are near the bandit camp...", 400);
    state_dialog_putline("Bandits camp here.", 400);
    state_dialog_putline("You could fight them for gold.", 400);

    state_dialog_ask("Do you want to fight?");
    if(dialog.chosen_answer == 1)
        result = state_battle_start("bandit #" + itoa(quests.killed_bandits+1), rand(1,3));
        if (result)
            quests.killed_bandits++;
        end
    else 
        state_dialog_putline("");
        state_dialog_putline("Yeah, let's get out of here...", 400);        
    end

    if (quests.killed_bandits >=10 && !map.area_unlocked[LOCATION_BANDIT_BOSS])
        script_place_bandit_camp_end_text();
    end


    game.state = STATE_MAP_ID;
END

FUNCTION script_place_bandit_camp_end_text()

BEGIN

    state_dialog_putline("The bandits are starting to fear you. You follow them", 400);
    state_dialog_putline("to their lair. That's where their boss is hiding.", 400);
    state_dialog_putline("", 400);

    sound_play(snd.win);
    state_dialog_putline("New area unlocked: Bandit boss lair", 400);

    map.area_unlocked[LOCATION_BANDIT_BOSS] = 1;
END