%设计举例：通带频率wp=0.2pi 通带频率波动  Ap=0.25dB 阻带截止频率0.3pi,阻带衰减As=50dB
%设计思路：
wp=0.2*pi;
ws=0.3*pi;
%求出过渡带
tr_width=ws-wp;
%查表查出来的，冲激响应长度
M=ceil(6.6*pi/tr_width)+1;
n=[0:1:M-1];
%通带的截止频率
wc=(ws+wp)/2;
%得到理想低通滤波器的单位冲激响应为
%-------------------------------
hd=idea_test(wc,M)
%-------------------------------
w_ham=(hamming(M))';
h=hd'*w_ham
%stem(n,h) 
%-------------------------------

[H,w]=freqz(h,[1],1000,'whole');
H=(H(1:1:501))';
w=(w(1:1:501))';
mag=abs(H);
db=20*log10((mag+eps)/max(mag));
pha=angle(H);
grd=grpdelay(h,[1],w);
%-------------------------------

delta_w=2*pi/1000
Rp=-(min(db(1:1:wp/delta_w+1)))
As=-round(max(db(ws/delta_w+1:1:501)))


subplot(221)
stem(n,hd)

subplot(222)
stem(n,w_ham)
subplot(223)
stem(n,h)

subplot(224)
plot(w/pi,db)
