%常用函数  boxcar(N) 返回N点矩形窗口函数
%         triang(N)返回N的三角函数
%         hanning(N) 返回汉明窗
%         blackman(N)布莱克曼窗
%         kaiser(N)凯泽窗
%=========================================
%窗函数举例
%   设计N=11   wc=02pi
wc=01.*pi;
N=11;
tao=(N-1)/2;
n=[0:(N-1)];
m=n-tao +eps;%加小数，避免0
hd=sin(wc*m)./(pi*m); %
n=1:N;
wdjx=boxcar(N);
wdsj=triang(N);
wdhm=hanning(N);
wdbm=blackman(N);
wdha=hamming(N);

[H1,w]=freqz(wdjx,1);
[H2,w]=freqz(wdsj,1);
[H3,w]=freqz(wdhm,1);
[H4,w]=freqz(wdbm,1);
[H5,w]=freqz(wdha,1);
subplot(221)
%窗函数图像
plot(n,[wdjx,wdsj,wdhm,wdbm,wdha]);
%幅频特性
subplot(222)
plot(w,[H1,H2,H3,H4,H5]);
%分贝
subplot(223)
%plot(w,20*log10*(abs(H2)))
%plot(w,[20*log10(abs(H1)),20*log10(abs(H2)),':',20*log10(abs(H3)),'-',20*log10(abs(H4)),'-.',20*log10(abs(H5)),'.'])
plot(w,20*log10(abs(H1)),w,20*log10(abs(H2)),':',w,20*log10(abs(H3)),'-',w,20*log10(abs(H4)),'-.',w,20*log10(abs(H5)),'.')