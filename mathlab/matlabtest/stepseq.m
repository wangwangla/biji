%单位阶跃函数
%实现原理，做逻辑运算
%移位  起始 结尾 
n0=0
n1=-5
n2=5
%function[x,n]=stepseq(n0,n1,n2)
n=[n1:n2]
x=[(n-n0)>=0]
stem(n,x)