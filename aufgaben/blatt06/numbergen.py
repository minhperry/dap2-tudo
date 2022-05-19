def generate(l: list):
    return l[0]*(256**3)+l[1]*(256**2)+l[2]*(256)+l[3]*1


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

# VẤN ĐỀ: KHÔNG THỂ SORT ĐÚNG NẾU 2 SỐ CÙNG CHUNG BYTE
# VD [1, 1, 2, 3] và [1, 2, 2, 3] -> sort sai
# https://i.imgur.com/AGBMkfV.png

# chạy file:
# python3 numbergen.py > numbers.txt -> tạo số
# javac SortByByte.java && (cat numbers.txt | java -ea SortByByte)

for num in lst2:
    print(generate(num))
