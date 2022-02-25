def coder(string):
    string = string.replace("m", "0")
    string = string.replace("u", "1")
    string = string.replace("r", "2")
    string = string.replace("c", "3")
    string = string.replace("i", "4")
    string = string.replace("e", "5")
    string = string.replace("l", "6")
    string = string.replace("a", "7")
    string = string.replace("g", "8")
    string = string.replace("o", "9")

    print(string)


phrase = input('Enter your phrase: ')
print('The phrase in code is:', coder(phrase))
