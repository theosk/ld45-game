CONST
    STATUS_START = 0;
    STATUS_ENDED = 2000;
    STATUS_FAILED = 9999;

    STATE_INTRO_ID = 0;
    STATE_DIALOG_ID = 1;
    
GLOBAL

struct game;
    int state = STATE_INTRO_ID;
    int font;
end

struct quests;
    int main = STATUS_START;
    int saveMerchant = STATUS_START;
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

int img_bg;