%���ú���  boxcar(N) ����N����δ��ں���
%         triang(N)����N�����Ǻ���
%         hanning(N) ���غ�����
%         blackman(N)����������
%         kaiser(N)����
%=========================================
%����������
%���Ŀ��Ϊ45�����ȡ�
N=45;
n=1:N;
wdjx=boxcar(N);
wdsj=triang(N);
wdhm=hanning(N);
wdbm=blackman(N);
wdha=hamming(N);
plot(n,[wdjx,wdsj,wdhm,wdbm,wdha]);