FUNCTION script_place_demon_fields();

PRIVATE
    int result;
BEGIN
    game.state = STATE_DIALOG_ID;
    dialog_putline("You are in the demon fields...", 400);
    dialog_putline("Demons are stronger than any other enemy.", 400);
    dialog_putline("You can fight them for gold.", 400);

    state_dialog_ask("Do you want to fight?");
    if(dialog.chosen_answer == 1)
        result = state_battle_start("demon #" + itoa(quests.killed_demons+1), rand(4,8));
        if (result)
            quests.killed_demons++;
        end
    else 
        dialog_putline("", 0);
        dialog_putline("Yeah, let's get out of here...", 400);        
    end

    if (quests.killed_demons >=4 && !map.area_unlocked[LOCATION_DEMON_LAIR])
        script_place_demon_fields_end_text();
    end


    game.state = STATE_MAP_ID;
END

FUNCTION script_place_demon_fields_end_text()

BEGIN

    dialog_putline("You defeated a bunch of demons.", 400);
    dialog_putline("The path to the demon lair is clear...", 400);
    dialog_putline("", 400);

    play_wav(snd.win, 0, 0);
    dialog_putline("Last area is unlocked: Demon lair!", 400);

    map.area_unlocked[LOCATION_DEMON_LAIR] = 1;
END