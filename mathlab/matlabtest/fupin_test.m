%�����Ƶ����
%������
    %b=[-1,2,4,2,-1],a=1
b=[-1,2,4,2,-1];
a=1;
%Ƶ������
[H,w]=freqz(b,a);
%��Ƶ����
subplot(221);
plot(w,abs(H));
subplot(222);
plot(w,H);
%��Ƶ����
subplot(223);
plot(w,-2*w) 



