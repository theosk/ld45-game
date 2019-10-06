FUNCTION script_intro()
BEGIN
    game.state = STATE_DIALOG_ID;
    script_intro_text();
    state_dialog_ask("Do you need to read all this again?");
    if(dialog.chosen_answer == 1)
        state_dialog_putline("", 400);
        state_dialog_putline("Ok, here we go again...", 400);
        state_dialog_putline("", 400);
        script_intro();
    else 
        state_dialog_putline("", 400);
        state_dialog_putline("And so, your adventure begins...", 2000);
    end
    state_dialog_clear();
    game.state = STATE_MAP_ID;
    map.area_unlocked[LOCATION_SHORE] = 1;
END

FUNCTION script_intro_text();

BEGIN
    state_dialog_putline("Centuries ago, twelve elemental crystals were spread across", 400);
    state_dialog_putline("the world. Their magic was meant to be used for the greater", 400);
    state_dialog_putline("good.", 400);
    state_dialog_putline("", 400);
    state_dialog_putline("But power thirst led to countless wars, and most of the", 400);
    state_dialog_putline("crystals were destroyed. Today, two of them remain:", 400);
    state_dialog_putline("the thunder crystal and the blood shard. Two opposing ", 400);
    state_dialog_putline("factions fight for supremacy, sweeping most of the world", 400);
    state_dialog_putline("in the process.", 400);
    state_dialog_putline("", 400);
    state_dialog_putline("You were barely able to leave your hometown before it was", 400);
    state_dialog_putline("destroyed. Without any luggage or goods, your boat heads to", 400);
    state_dialog_putline("unknown lands, hoping the war will not have arrived there.", 400);
END