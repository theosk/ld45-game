CONST
    STATUS_START = 0;
    STATUS_ENDED = 2000;
    STATUS_FAILED = 9999;

    STATE_INTRO_ID = 0;
    STATE_DIALOG_ID = 1;
    STATE_BATTLE_ID = 2;
    STATE_MAP_ID = 3;
    
GLOBAL

struct game;
    int state = STATE_INTRO_ID;
    int font;
end

struct quests;
    int main = STATUS_START;
    int saveMerchant = STATUS_START;
end

struct inventory;
    int gold = 0;
    int dagger = 0; // 1d6 attack
    int sword = 0; // + 1d4 attack for offhand weapon;
    int amulet = 0; // +1 attack
    int weddingRing = 0; // +1 attack
    int shield = 0; // + 1 attack for some reason
    int treasureMap = 0;
end

struct battle;
    string enemy_name;
    int enemy_power;
    int enemy_health;
    int player_health;
end

struct dialog;
    int currentLine;
    string line_text[10];
    int line_id[10];

    text_offset_x = 24;
    text_offset_y = 22;
    text_vertical_space = 38;

    int answered = 0;
    int chosen_answer = 0;
    
end

struct map;
    int ui_id;
end


int img_bg;
int img_logo;