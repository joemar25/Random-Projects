# class variable
class Variable():

    def __init__(self, var: str, dom: int = 1) -> None:
        self.__set_var(var)
        self.dom = dom

    def __set_var(self, variable: str) -> None:
        self.__var = variable

    def get_var(self) -> str:
        return self.__var

# constraint(s)


def a_greater_than_b(a: int, b: int) -> bool:
    return a > b


def b_not_equal_c(b: int, c: int) -> bool:
    return b != c


def a_not_equal_c(a: int, c: int) -> bool:
    return a != c


def isSatisfied(a: int = 0, b: int = 0, c: int = 0) -> bool:
    if not a_greater_than_b(a, b):
        return False
    if not b_not_equal_c(b, c):
        return False
    if not a_not_equal_c(a, c):
        return False
    return True


def print_variables(var: Variable) -> None:
    print('solution:')
    for i in var:
        print(f'\t  {i.get_var()} = {i.dom}')


def change_value(domain: int):
    return domain + 1


# main function
def main():
    # V - Variables
    var = [
        Variable('a'),
        Variable('b'),
        Variable('c'),
    ]

    # D - Domains
    dom = [1, 2, 3]

    # C - Constraints
    constraint_satisfied: bool = False

    # S - Solution to Satisfy C using backtracking (systematic)
    while not constraint_satisfied:

        for i in range(len(dom)):
            if var[0].dom <= var[1].dom:
                var[0].dom = dom[i]

        var[2].dom = dom[i]

        if isSatisfied(var[0].dom, var[1].dom, var[2].dom) == True:
            constraint_satisfied = True
            break

    print('satified_constraint:', constraint_satisfied)
    print_variables(var)
    # answer must be: a - 2, b - 1, c - 3


if __name__ == "__main__":
    main()
