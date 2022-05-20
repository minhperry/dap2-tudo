import random as r


def generate(l: list):
    return l[0]*(256**3)+l[1]*(256**2)+l[2]*(256)+l[3]*1


def shufgen(l: list, shuf: bool):
    if shuf == True:
        r.shuffle(l)
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
    [127, 255, 255, 255],
    [128, 0, 0, 0]
]

# limit: i <= 127
lst3 = [[i, int(i/2), 2*i+2, int(i/3)-3] for i in range(30, 100)]

# limit: i < 64 (4i < 256)
lst4 = [[i, 2*i, 3*i, 4*i] for i in range(0, 60)]

# limit: i <= 127
lst5 = [[i, int(i/2), int(i/3), int(i/4)] for i in range(60, 100)]

# -> first byte should always be smaller than 128
# else Long data is needed

lst6 = []

lst7 = [[r.randint(0, 127), r.randint(0, 255), r.randint(0, 255), r.randint(0, 255)] for _ in range(0, 40)]

# generate (shuffled) list
# python3 numbergen.py > numbers.txt

shufgen(lst7, True)



# for i in {1..5}; do (seq 1000000 10000 10000000 | shuf | java *SDRadixSort); done

# seq 1000000 100000 10000000 | shuf
# In 10^3ns
# LSD: 131 / 102 / 85 / 103 / 101
# MSD: 1551 / 1694 / 1610 / 1519 / 1578

# seq mit 10000 je Schritt
# In 10^3ns
# LSD: 663 / 648 / 569 / 652 / 673
# MSD: 6018 / 6087 / 5908 / 5698 / 5760
