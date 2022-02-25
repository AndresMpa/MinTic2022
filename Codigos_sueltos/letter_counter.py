def vowels_counter(string_with_vowels):
    string_with_vowels = string_with_vowels.lower()
    vowels = [0, 0, 0, 0, 0]

    for vowel in string_with_vowels:
        if vowel == "a":
            vowels[0] += 1
        if vowel == "e":
            vowels[1] += 1
        if vowel == "i":
            vowels[2] += 1
        if vowel == "o":
            vowels[3] += 1
        if vowel == "u":
            vowels[4] += 1

    return vowels


string = input("Write a sentence: ")
number_of_vowels = vowels_counter(string)
print('There are ', number_of_vowels[0], ' vowels a')
print('There are ', number_of_vowels[1], ' vowels e')
print('There are ', number_of_vowels[2], ' vowels i')
print('There are ', number_of_vowels[3], ' vowels o')
print('There are ', number_of_vowels[4], ' vowels u')
