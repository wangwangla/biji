%��ӱ����Ƕ��ߵĳ�������ͬ�� �������ȱȽϳ���С��Ȼ���ڽ����ǽ�������
function[y,n]=sigadd(x1,x2,n1,n2)
%�õ���n�ķ�Χ������С��ֵ���������ֵ
n=min(min(n1),min(n2)):max(max(n1),max(n2))
y=zeros(1,length(n))
y2=y1
y1(find((n>=min(n))&(n<=max(n1)==1))=x1
y2(find((n>=min(n))&(n<=max(n1)==1))=x1
y=y1+y2