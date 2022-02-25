"""
I made a prime number decomposer
"""


def decomposer(number):
    i = 1
    factors = []
    while i <= number:
        if number % i == 0:
            factors.append(i)
            number /= i
        i += 1
    return factors


if __name__ == "__main__":
    print(decomposer(int(input("Append a number: "))))

