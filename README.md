# JavaMySqlLookupAgent

Using a SQL database, we record the moves of the players in "2-PLAYER" mode.
# 2_PLAYER MODE
Simple tic tac toe clickable board.
Each move is recorded from the board into a sql database.
scehma:
moveno,x,y,player,win_bit

# moveno: 
range: 0-8
The move number in the current game.

# x,y
co-ordinate [x,y] on the board. Range [0,0] to [2,2].

# player
Player who played the move. [1] or [2].

# win_bit
if the move resulted in a win.

# Single Player Mode:
Starts with player-1 (human_move)
The next computer move is picked on the basis of the previously recorded moves in the DB.
Heuristics: Which move is the best in terms of win_bit and the move played by the user.
