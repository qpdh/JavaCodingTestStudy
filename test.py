def test1():
    print("this is test 1")
    test2()


def test2():
    """
    대충 복잡한 연산
    """
    a = 1+1
    print("this is test 2")


if __name__ == "__main__":
    print("this is main")
    test1()
