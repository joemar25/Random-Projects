""""
 algorithm to avopid deadlock processes on cpu
  made by Joemar
  
  @todo
    set the maximum resources (a=printer, etc...)
    calculate the initial work
"""


class Process():

    def __init__(self, pid, alloc, maximum, work=None):
        # need to fill
        self.___set_id(pid)
        self.___set_alloc(alloc)
        self.___set_max(maximum)

        # defining the need to do the job of a specific process -> Needed = Maximum – Allocated.
        self.need = [0, 0, 0]
        for i in range(3):
            self.need[i] = maximum[i] - alloc[i]

        # mandatory, mainly the first process is required
        self.work = [0, 0, 0] if (work is None) else work

    def ___set_id(self, _id):
        self.pid = _id

    def ___set_alloc(self, _alloc):
        self.alloc = _alloc

    def ___set_max(self, _max):
        self.max = _max


def display(data):
    data.sort(key=lambda list: list.pid)
    print("process      alloc       max     need      work")
    for _p in data:
        print(f"{_p.pid}          {_p.alloc} {_p.max} {_p.need} {_p.work}")


def safe_list(data):
    print("\t\t<", end="")
    for _p in data:
        print(f" {_p.pid} ", end="")
    print(">")


def algo(unsafe):

    print("\n\t\t   UNSAFE LIST")
    display(unsafe)

    safe = []
    work_list = []

    i = w_len = 0
    size = len(unsafe)
    work_list.append(unsafe[0].work)

    while size != 0:
        if i == size:
            i = 0

        if unsafe[i].need > work_list[w_len]:
            i += 1
        else:
            temp = [0, 0, 0]
            for j in range(3):
                temp[j] += work_list[w_len][j] + unsafe[i].alloc[j]
            work_list.append(temp)
            unsafe[i].work = temp

            safe.append(unsafe[i])
            unsafe.pop(i)

            w_len += 1
            size -= 1

    print("\n\t\t     SAFE LIST")
    safe_list(safe)
    display(safe)


# Main
processList = []
processList.append(Process(0, [0, 1, 0], [7, 5, 3], [3, 3, 2]))
processList.append(Process(1, [2, 0, 0], [3, 2, 2]))
processList.append(Process(2, [3, 0, 2], [9, 0, 2]))
processList.append(Process(3, [2, 1, 1], [2, 2, 2]))
processList.append(Process(4, [0, 0, 2], [4, 3, 3]))

# Do Algo
algo(processList)
