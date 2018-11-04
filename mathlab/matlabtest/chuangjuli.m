%常用函数  boxcar(N) 返回N点矩形窗口函数
%         triang(N)返回N的三角函数
%         hanning(N) 返回汉明窗
%         blackman(N)布莱克曼窗
%         kaiser(N)凯泽窗
%=========================================
%窗函数举例
%窗的宽度为45【长度】
N=45;
n=1:N;
wdjx=boxcar(N);
wdsj=triang(N);
wdhm=hanning(N);
wdbm=blackman(N);
wdha=hamming(N);
plot(n,[wdjx,wdsj,wdhm,wdbm,wdha]);