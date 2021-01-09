# APC-home-assignment

Inside a chipset producing factory, there are older and newer machines. Newer machines are able to produce
a higher number chipsets per minute, but both new and old machines consume the same amount of energy
per hour. Given a list of N machines by the number of chipsets/minute, detect the machines that should be
started to produce K chipsets per minute with minimum waste. In case there are multiple solutions, all the
possible solutions will be displayed.  

Input file format:  
First line contains the number of machines  
Second line contains the chipset/minute for every machine  
Third line contains number of chipsets to be produced.  

Output file format :  
First line contains nr of solutions  
Next lines contains each solution  
Last line contains the waste(nr of extra pieces)  

Example input:  
6  
1 2 4 10 5 6  
11  

Example output:  
Nr solutions=4  
10 1  
2 4 5  
1 4 6  
5 6  
Waste=0  