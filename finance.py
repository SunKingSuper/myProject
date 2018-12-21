#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Sat Dec  1 21:44:33 2018

@author: athena
"""

import matplotlib.pyplot as plt;
import math

def actualInterestRate(normalInterestRate, n):
    return math.pow(1+normalInterestRate , n) - 1




principal = 1359
DiscountRate = 0.01
period = 3
JDchargeRate = 0.005

repayment = [0]
profit = [principal]
epxectedIncome = [0]
print(actualInterestRate(DiscountRate, 12))

for i in range(period):
    income = profit[i] * (1 + DiscountRate)
    repay = principal / period + principal * JDchargeRate
    eachProfit = income - repay
    repayment.append(repay)
    epxectedIncome.append(income)
    profit.append(eachProfit)
    
    
a = plt.figure()
plt.hold(a)
plt.plot(repayment,'.-')
plt.plot(profit,'o-')
plt.plot(epxectedIncome,'x-')
plt.legend(["repayment","profit","epxectedIncome"])
print(repayment, "\n", profit, "\n", epxectedIncome)