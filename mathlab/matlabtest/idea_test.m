function hd = idea_test(wc,M);
alpha=(M-1)/2;
n=[0:1:(M-1)];
m=n-alpha;
fc=wc/pi;
%hd=(sinc(wc*m))/(pi*m);
hd=fc*sinc(fc*m);