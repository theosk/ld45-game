FUNCTION state_battle_start(string enemy_name, int enemy_power)

PRIVATE
    int result;
    string text;

BEGIN
    battle.enemy_name = enemy_name;
    battle.enemy_power = enemy_power;
    battle.enemy_health = 5;
    battle.player_health = 5;

    game.state = STATE_DIALOG_ID;
    if(!mouse.right) frame(2000); end

    state_dialog_putline("", 100);
    state_dialog_putline("Battle against " + enemy_name + " begins!", 1000);
    state_dialog_putline("", 100);
    
    loop
        state_battle_turn();
        if(!mouse.right) frame(5000); end
        
        if (battle.player_health == 0)
            // LOST
            sound_play(snd.hit[3]);
            state_dialog_putline(enemy_name + " has defeated you!", 400);
            state_dialog_putline("You just LOST the battle!!!", 400);
            state_dialog_putline("You retreat. Better luck next time.", 200);
            result = 0;
            break;
        end

        if (battle.enemy_health == 0)
            // WON
            sound_play(snd.win);
            state_dialog_putline(enemy_name + " is defeated!", 400);
            state_dialog_putline("You just WON the battle!!!", 200);
            state_battle_generate_reward();
            result = 1;
            break;
        end
        frame;
    end

    state_dialog_waitkey();

    return(result);
END

FUNCTION state_battle_turn()

PRIVATE
    int roll;

BEGIN
    sound_play(snd.hit[rand(0,6)]);
    roll = rand(1, 6);
    if(inventory.sword) roll += rand(1,6); end
    if(inventory.amulet) roll++; end
    if(inventory.weddingRing) roll++; end
    if(inventory.shield) roll++; end

    if (roll > battle.enemy_power)
        // Player hits
        state_battle_generateline("PLAYER", battle.enemy_name);
        battle.enemy_health--;
        
    else
        // Enemy hits
        state_battle_generateline(battle.enemy_name, "PLAYER");
        battle.player_health--;
    end
END


FUNCTION state_battle_generateline(string attacker_name, string target_name)

BEGIN
    x = rand(0, 10);
    switch (x)
        case 0:
            state_dialog_putline(attacker_name + " hits " + target_name);
            end

        case 1:
            state_dialog_putline(attacker_name + " slashes " + target_name);
            end

        case 2:
            state_dialog_putline(attacker_name + " overpowers " + target_name);
            end

        case 3:
            state_dialog_putline(attacker_name + " bites " + target_name);
            end

        case 4:
            state_dialog_putline(attacker_name + " kicks " + target_name + " in the butt");
            end

        case 5:
            state_dialog_putline(target_name + " trips and takes damage");
            end

        case 6:
            state_dialog_putline(attacker_name + " slaps " + target_name);
            end

        case 7:
            state_dialog_putline(target_name + " has digestive issues and takes damage");
            end

        case 8:
            state_dialog_putline(attacker_name + " shouts really hard and damages " + target_name);
            end

        case 9:
            state_dialog_putline(attacker_name + " sets a trap and " + target_name + " steps in it");
            end

        case 10:
            state_dialog_putline(attacker_name + " gives " + target_name + " some poisonous cake");
            end
        
    END
END

function state_battle_generate_reward()
PRIVATE
    int sum;
BEGIN
    sum = rand(1,6); //1d6
    sum += battle.enemy_power * rand(1,6);
    inventory.gold += sum;
    sound_play(snd.coin);
    state_dialog_putline("Coins obtained: " + itoa(sum), 200);
END