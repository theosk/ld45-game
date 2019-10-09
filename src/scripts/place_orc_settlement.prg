
FUNCTION script_place_orc_settlement();
PRIVATE
    int result;
BEGIN
    game.state = STATE_DIALOG_ID;
    dialog_putline("You are near the orc settlement...", 400);
    dialog_putline("Orcs are tougher than bandits, but also richer.", 400);
    dialog_putline("You could fight them for gold.", 400);

    state_dialog_ask("Do you want to fight?");
    if(dialog.chosen_answer == 1)
        result = state_battle_start("orc #" + itoa(quests.killed_orcs+1), rand(1,3));
        if (result)
            quests.killed_orcs++;
        end
    else 
        dialog_putline("", 0);
        dialog_putline("Yeah, let's get out of here...", 400);        
    end

    if (quests.killed_orcs >=10 && !map.area_unlocked[LOCATION_ORC_TREASURE_ROOM])
        script_place_orc_settlement_end_text();
    end


    game.state = STATE_MAP_ID;
END

FUNCTION script_place_orc_settlement_end_text()

BEGIN

    dialog_putline("You defeated lots of orcs. You explore their lands.", 400);
    dialog_putline("You find where they hide their treasure...", 400);
    dialog_putline("", 400);

    play_wav(snd.win, 0, 0);
    dialog_putline("New area unlocked: Orc treasure room", 400);

    map.area_unlocked[LOCATION_ORC_TREASURE_ROOM] = 1;
END