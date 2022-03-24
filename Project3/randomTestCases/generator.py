import random

for i in range(5):
    file = open("myinput"+str(i)+".txt", 'w')

    file.write(str(random.randint(0, 100000000))+"\n")
   
    fee = random.randint(0,500)
    users = random.randint(10,500)
    queries = random.randint(20,4000)
  
    file.write(str(fee)+" "+str(users)+" "+str(queries)+"\n")

    for ij in range(users):
        dollar = random.randint(0,500)
        coin = random.randint(0,100)

        file.write(str(round(dollar, 2))+" "+str(round(coin, 2))+"\n")

    for ij in range(queries):
        type = random.randint(1,26)

        if (type == 1 or type == 15 or type == 19 or type == 23):
            id = random.randint(0, users-1)
            d1 = random.random() * 100
            d2 = random.random() * 100

            file.write("10 "+str(id)+" "+str(round(d1,2))+" "+str(round(d2,2))+"\n")

        elif (type == 2 or type == 16 or type == 20 or type == 24):
            id = random.randint(0, users-1)
            d1 = random.random() * 100

            file.write("11 "+str(id)+" "+str(round(d1,2))+"\n")

        elif (type == 3 or type == 17 or type == 21 or type == 25):
            id = random.randint(0, users-1)
            d1 = random.random() * 100
            d2 = random.random() * 100

            file.write("20 "+str(id)+" "+str(round(d1,2))+" "+str(round(d2,2))+"\n")

        elif (type == 4 or type == 18 or type == 22 or type == 26):
            id = random.randint(0, users-1)
            d1 = random.random() * 100

            file.write("21 "+str(id)+" "+str(round(d1,2))+"\n")

        elif (type == 5):
            id = random.randint(0, users-1)
            d1 = random.random() * 10
            
            file.write("3 "+str(id)+" "+str(round(d1,2))+"\n")
        
        elif (type == 6):
            id = random.randint(0, users-1)
            d1 = random.random() * 10

            file.write("4 "+str(id)+" "+str(round(d1,2))+"\n")

        elif (type == 7):
            id = random.randint(0, users-1)

            file.write("5 "+str(id)+"\n")

        elif (type == 8):
            file.write("777\n")

        elif (type == 9):
            d1 = random.random() * 10

            file.write("666 "+str(round(d1, 2))+"\n")

        elif (type == 10):
            file.write("500\n")

        elif (type == 11):
            file.write("501\n")

        elif (type == 12):
            file.write("502\n")

        elif (type == 13):
            file.write("505\n")
        
        elif (type == 14):
            file.write("555\n")
        
    file.close()
        