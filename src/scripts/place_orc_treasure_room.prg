FUNCTION script_place_orc_treasure_room();


BEGIN
    game.state = STATE_DIALOG_ID;
     
    if (inventory.treasureMap)
        script_place_orc_treasure_room_empty_text();
    else
        script_place_orc_treasure_room_item_text();
    end

    state_dialog_clear();
    game.state = STATE_MAP_ID;
END

FUNCTION script_place_orc_treasure_room_empty_text()

BEGIN
    state_dialog_putline("You find nothing of value in this place. The smell is...", 400);
    state_dialog_putline("just too much to handle.", 400);
    state_dialog_putline("You die...", 400);
    state_dialog_putline("Just kidding. But you leave.", 400);
    state_dialog_waitkey();

END


FUNCTION script_place_orc_treasure_room_item_text()

BEGIN
    state_dialog_putline("You find a lot of useless crap. Please, don't ask.", 400);
    state_dialog_putline("You finally find a map. A treasure map. In points to", 400);
    state_dialog_putline("THE SHORE...", 400);
    inventory.treasureMap = 1;
    state_dialog_waitkey();
END