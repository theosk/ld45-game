FUNCTION script_place_weapon_shop();

BEGIN
    game.state = STATE_DIALOG_ID;

    state_dialog_clear();
    dialog_putline("You enter the weapon store.", 400);
    dialog_putline("They only sell swords. Just one model.", 400);

    if (inventory.sword)
        dialog_putline("You already have that sword. Let's just leave.", 400);
    elseif (inventory.gold < shops.price_sword)
        script_place_weapon_shop_no_money_text();
    else
        script_place_weapon_shop_buying_text();
    end

    state_dialog_waitkey();
    state_dialog_clear();

    game.state = STATE_MAP_ID;
END


FUNCTION script_place_weapon_shop_buying_text()

BEGIN
    dialog_putline("The sword costs " + itoa(shops.price_sword) + " coins.", 400);
    dialog_putline("It gives you an extra 1d6 attack dice", 400);
    state_dialog_ask("Will you buy it?");
    if(dialog.chosen_answer == 1)
        inventory.sword = 1;
        inventory.gold -= shops.price_sword;
        dialog_putline("You got a SWORD! Your attacks gain 1D6 points.", 400);
        play_wav(snd.win, 0, 0); 
    else 
        dialog_putline("Ok, we have nothing to do here then.", 400);
    end
END

FUNCTION script_place_weapon_shop_no_money_text()

BEGIN
    dialog_putline("The sword costs " + itoa(shops.price_sword) + " coins.", 400);
    dialog_putline("You can't afford it right now. Come back later.", 400);
END

