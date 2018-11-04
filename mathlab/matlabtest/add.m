%加法，将相应位置加起来，其他位为0
n1=[-2:4];
n2=[0:3];
x1=[2,-1,4,1,-1,1,-2];
x2=[8,-2,0,-2];
n=min(min(n1),min(n2)):max(max(n1),max(n2));
y1=zeros(1,length(n));
y2=y1
y1(find((n>=min(n1))&(n<=max(n1))==1))=x1;
y2(find((n>=min(n2))&(n<=max(n2))==1))=x2;
%相减
y=y1-y2
%x相加
y=y1+y2
%相乘
y=y1.*y2


