%ָ������
%����  n1=��ʼ  n2=����
a=3
n1=-5
n2=5
%function[x,n]=zhishuseq(a,n1,n2)
n=[n1:n2]
x=(a).^n
stem(n,x)