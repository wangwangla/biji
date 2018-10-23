%��ƾ�����ͨ��Ƶ��wp=0.2pi ͨ��Ƶ�ʲ���  Ap=0.25dB �����ֹƵ��0.3pi,���˥��As=50dB
%���˼·��
wp=0.2*pi;
ws=0.3*pi;
%������ɴ�
tr_width=ws-wp;
%���������ģ��弤��Ӧ����
M=ceil(8*pi/tr_width);
%ͨ���Ľ�ֹƵ��
wc=(ws+wp)/2;
alpha=(M-1)/2;
n=[0:1:(M-1)];
m=n-alpha;
fc=wc/pi;
%hd=(sinc(wc*m))/(pi*m);
hd=fc*sinc(fc*m);
%���������
w_ham=(hamming(M))';
%���
h=hd.*w_ham;
[H,w]=freqz(h,[1],1000,'whole');
H=(H(1:1:501))';
w=(w(1:1:501))';
mag=abs(H);
db=20*log10((mag+eps)/max(mag));
pha=angle(H);
grd=grpdelay(h,[1],w);
%��ͼ
subplot(221)
stem(n,hd)
subplot(222)
stem(n,w_ham)
subplot(223)
stem(n,h)
subplot(224)
plot(db)