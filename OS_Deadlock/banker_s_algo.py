""""
 algorithm to avoid deadlock processes on cpu
  made by Joemar
"""


class Process:

    def __init__(self, id, alloc, max, work=[0, 0, 0]):
        # need to fill
        self.id = id
        self.alloc = alloc
        self.max = max
        # defining the need to do the job of a specific process -> Needed = Maximum – Allocated.
        self.need = [0, 0, 0]
        for i in range(3):
            self.need[i] = max[i] - alloc[i]
        # mandatory, mainly the first process is required
        self.work = work


def display(list):
    list.sort(key=lambda list: list.id)
    print("process      alloc       max     need      work")
    for p in list:
        print(f"{p.id}          {p.alloc} {p.max} {p.need} {p.work}")


def safeList(list):
    print("\t\t<", end="")
    for p in list:
        print(f" {p.id} ", end="")
    print(">")


def algo(unsafe):

    print("\n\t\t   UNSAFE LIST")
    display(unsafe)

    safe = []
    workList = []

    i = wLen = 0
    n = len(unsafe)
    workList.append(unsafe[0].work)

    while n != 0:
        if i == n:
            i = 0

        if unsafe[i].need > workList[wLen]:
            i += 1
        else:
            temp = [0, 0, 0]
            for j in range(3):
                temp[j] += workList[wLen][j] + unsafe[i].alloc[j]
            workList.append(temp)
            unsafe[i].work = temp

            safe.append(unsafe[i])
            unsafe.pop(i)

            wLen += 1
            n -= 1

    print("\n\t\t     SAFE LIST")
    safeList(safe)
    display(safe)


# Main
processList = []
processList.append(Process('0', [0, 1, 0], [7, 5, 3], [3, 3, 2]))
processList.append(Process('1', [2, 0, 0], [3, 2, 2]))
processList.append(Process('2', [3, 0, 2], [9, 0, 2]))
processList.append(Process('3', [2, 1, 1], [2, 2, 2]))
processList.append(Process('4', [0, 0, 2], [4, 3, 3]))


# Do Algo
algo(processList)
