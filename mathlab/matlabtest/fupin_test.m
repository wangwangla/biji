%计算幅频特性
%举例：
    %b=[-1,2,4,2,-1],a=1
b=[-1,2,4,2,-1];
a=1;
%频率特性
[H,w]=freqz(b,a);
%幅频曲线
subplot(221);
plot(w,abs(H));
subplot(222);
plot(w,H);
%相频曲线
subplot(223);
plot(w,-2*w) 



