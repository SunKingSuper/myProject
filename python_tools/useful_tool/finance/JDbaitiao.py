import mathplotlib.pyplot as plt;

principal = 1000
DiscountRate = 0.05
period = 3
JDchargeRate = 0.05

repayment = [0]
profit = [B]
epxectedIncome = [0]

for i in range(period):
    income = profit[i] * (1 + DiscountRate)
    repay = principal / period + principal * JDchargeRate
    eachProfit = income - repay
    repayment.append(repay)
    epxectedIncome.append(income)
    profit.append(eachProfit)
    
