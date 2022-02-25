import numpy as np
import math as mt


# It draws the initial board
def empty_board(size: tuple) -> None:
    for _ in range(0, size[0]):
        for _ in range(0, size[1]):
            print("|_", end="")
        print("|")


# It draws the board
def display_game_board(size: tuple, matrix) -> None:
    for lines_position in range(0, size[0]):
        for rows_position in range(0, size[1]):
            if matrix[lines_position, rows_position] == 1:
                print("|X", end="")
            elif matrix[lines_position, rows_position] == 10:
                print("|O", end="")
            else:
                print("|_", end="")
        print("|")


def game_status(matrix, size: int) -> int:
    # Player X wins
    if 3 in np.sum(matrix, axis=0) or 3 in np.sum(matrix, axis=1) or np.sum(np.diagonal(matrix)) == 3 or \
            np.sum(np.diagonal(np.fliplr(matrix))) == 3:
        return 1
    # Player O wins
    elif 30 in np.sum(matrix, axis=0) or 30 in np.sum(matrix, axis=1) or np.sum(np.diagonal(matrix)) == 30 or \
            np.sum(np.diagonal(np.fliplr(matrix))) == 30:
        return 2
    # Players tie
    elif np.sum(matrix) == mt.ceil(size + size * 10) or np.sum(matrix) == mt.floor(size + size * 10):
        return 3
    # Playing
    else:
        return 4


def tic_tac_toe() -> None:
    status = 4
    turn = True
    width = int(input("Set the board width: "))
    height = int(input("Set the board height: "))
    matrix = np.zeros((width, height))

    empty_board((width, height))

    while status == 4:
        print("Type your next move")
        chosen_position = tuple((input("Line: "), input("Row: ")))
        board_position = int(chosen_position[0]) - 1, int(chosen_position[1]) - 1
        if 0 <= board_position[0] <= 2 and 0 <= board_position[1] <= 2:
            if matrix[board_position] == 0:
                if turn:
                    matrix[board_position] = 1
                else:
                    matrix[board_position] = 10
                status = game_status(matrix, (width * height) / 2)
                display_game_board((width, height), matrix)
                turn = not turn
            else:
                print("Remember: Use empty spaces")
        else:
            print("That box does not exist")
    if status == 1:
        print("Player X wins")
    elif status == 2:
        print("Player O wins")
    else:
        print("Players tie")


if __name__ == "__main__":
    playing = True
    while playing:
        tic_tac_toe()
