%相加必须是二者的长度是相同的 ，所以先比较出大小，然后在将他们进行扩大
function[y,n]=sigadd(x1,x2,n1,n2)
%得到了n的范围，从最小的值，到达最大值
n=min(min(n1),min(n2)):max(max(n1),max(n2))
y=zeros(1,length(n))
y2=y1
y1(find((n>=min(n))&(n<=max(n1)==1))=x1
y2(find((n>=min(n))&(n<=max(n1)==1))=x1
y=y1+y2