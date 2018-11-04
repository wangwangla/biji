%��ƾ�����ͨ��Ƶ��wp=0.2pi ͨ��Ƶ�ʲ���  Ap=0.25dB �����ֹƵ��0.3pi,���˥��As=50dB
%���˼·��
wp=0.2*pi;
ws=0.3*pi;
%������ɴ�
tr_width=ws-wp;
%��������ģ��弤��Ӧ����
M=ceil(6.6*pi/tr_width)+1;
n=[0:1:M-1];
%ͨ���Ľ�ֹƵ��
wc=(ws+wp)/2;
%�õ������ͨ�˲����ĵ�λ�弤��ӦΪ
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
