import random


def generate(l: list):
    return l[0]*(256**3)+l[1]*(256**2)+l[2]*(256)+l[3]*1


def shufgen(l: list, shuf: bool):
    if shuf == True:
        random.shuffle(l)
    for num in l:
        print(generate(num))


lst = [
    [0, 12, 96, 145],
    [8, 6, 16, 9],
    [91, 232, 145, 19],
    [60, 73, 100, 188],
    [99, 14, 207, 60],
    [48, 120, 222, 93],
    [37, 55, 102, 0],
    [1, 1, 2, 3]
]

lst2 = [
    [0, 0, 1, 1],
    [0, 0, 1, 2]
]

# limit: i <= 127
lst3 = [[i, i+1, i+2, i+3] for i in range(126, 130)]

# limit: i < 64 (4i < 256)
lst4 = [[i, 2*i, 3*i, 4*i] for i in range(44, 55)]

# limit: i <= 127
lst5 = [[i, int(i/2), int(i/3), int(i/4)]for i in range(60, 100)]

# -> first byte should always be smaller than 128
# else Long data is needed


# chạy file:
# python3 numbergen.py > numbers.txt -> tạo số

shufgen(lst5, False)
