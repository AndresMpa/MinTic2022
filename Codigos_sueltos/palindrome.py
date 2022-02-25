"""
I remembered a couple of exercises that I made in C++ second semester,
so I decided to improved it here in python, could made the same in C++
again... No sure; so I'm making palindrome detection function
"""


# Examples

palindrome_example_1 = "Mom"
palindrome_example_2 = "Anna"
palindrome_example_3 = "WoW"
palindrome_example_4 = "Step on no pets"
palindrome_example_5 = "Eva, can I see bees in a cave?"


def palindrome_for_words(word_to_check):
    iterator = len(word_to_check) - 1
    for letter in range(len(word_to_check)):
        if word_to_check[letter] != word_to_check[iterator]:
            return 0
        iterator -= 1
    return 1


def clean_expression(expression_to_clean):
    expression_to_clean = str(expression_to_clean)
    for character in expression_to_clean:
        if not character.isalpha():
            expression_to_clean = expression_to_clean.replace(character, '')
    return expression_to_clean.lower()


def check_palindrome(expression_to_check):
    if palindrome_for_words(clean_expression(expression_to_check)) == 1:
        print("The expression " + expression_to_check + " is palindrome")
    else:
        print("The expression " + expression_to_check + " is not a palindrome")


if __name__ == "__main__":
    check_palindrome(palindrome_example_1)
    check_palindrome(palindrome_example_2)
    check_palindrome(palindrome_example_3)
    check_palindrome(palindrome_example_4)
    check_palindrome(palindrome_example_5)

