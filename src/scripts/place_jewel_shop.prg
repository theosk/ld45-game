FUNCTION script_place_jewel_shop();

BEGIN
    game.state = STATE_DIALOG_ID;

    state_dialog_clear();
    state_dialog_putline("You enter the jewel store.", 400);
    state_dialog_putline("They only sell wedding rings. Just one model.", 400);

    if (quests.saveMerchant == STATUS_FAILED)
        script_place_jewel_ship_merchant_dead_text();
    elseif (inventory.weddingRing)
        state_dialog_putline("You already have one. Let's just leave.", 400);
    elseif (inventory.gold < shops.price_ring)
        script_place_jewel_shop_no_money_text();
    else
        script_place_jewel_shop_buying_text();
    end

    state_dialog_waitkey();
    state_dialog_clear();

    game.state = STATE_MAP_ID;
END

FUNCTION script_place_jewel_ship_merchant_dead_text()

BEGIN
    state_dialog_putline("But there's nothing to buy and no one is here.", 400);
    state_dialog_putline("The shop owner was that merchant you left to die in the", 400);
    state_dialog_putline("woods. That means no rings for you.", 400);
    state_dialog_putline("", 400);
    state_dialog_ask("Are you happy now?");
    state_dialog_putline("Well, I gues it doesn't matter now.", 400);

END


FUNCTION script_place_jewel_shop_buying_text()

BEGIN
    state_dialog_putline("The wing costs " + itoa(shops.price_ring) + " coins.", 400);
    state_dialog_putline("It gives you an extra point on attack rolls", 400);
    state_dialog_ask("Will you buy it?");
    if(dialog.chosen_answer == 1)
        inventory.weddingRing = 1;
        inventory.gold -= shops.price_ring;
        state_dialog_putline("You got a RING! Your attacks gain 1 point. Why not?.", 400);
        sound_play(snd.win); 
    else 
        state_dialog_putline("Ok, we have nothing to do here then.", 400);
    end
END

FUNCTION script_place_jewel_shop_no_money_text()

BEGIN
    state_dialog_putline("The ring costs " + itoa(shops.price_ring) + " coins.", 400);
    state_dialog_putline("You can't afford it right now. Come back later.", 400);
END

