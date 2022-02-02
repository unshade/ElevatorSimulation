#!/bin/bash
usage="run.sh <sourceDirectory> <IODirectory>"
if [ $# -ne 2 ]
then
    echo $usage ; exit 1
fi
root=$(pwd)
if cd $1
then
    src=$(pwd)
else
    exit 2
fi
cd $root
if cd $2
then
    io=$(pwd)
else
    exit 3
fi
cd $src
for f in Main.java Evenement.java Echeancier.java
do
    if ! cat $f > /dev/null
    then
	echo No file ${src}/${f} ; exit 4
    fi
done
rm -f *.class || exit 5
echo ----------
echo $(grep assert *.java | wc -l) asserts 
echo ----------
echo $(grep notYetImplemented *.java | wc -l) notYetImplemented
echo ----------
echo -n 'recompile ' 
if javac -encoding UTF-8 *.java >& /dev/null
then
    echo '(UTF-8) good'
elif javac -encoding ASCII *.java >& /dev/null
then
    echo '(ASCII) strange...'
elif javac -encoding ISO-8859 *.java >& /dev/null
then
    echo '(ISO-8859) windows... beurk'
else
    echo erreur: \(cd ${src} \; javac -encoding UTF-8 \*.java\)
    exit 6
fi
if ! cat ${io}/input.1 > /dev/null
then
    exit 1
fi
tmpu=/tmp/${USER}
rm -rf ${tmpu}
mkdir ${tmpu} >& /dev/null
simbox=~colnet5/SimBox/${USER}
if [ -d ~colnet5 ]
then
    if [ ! -d ${simbox} ]
    then
	mkdir ${simbox} 2>/dev/null
	chmod a+rwx ${simbox} 2>/dev/null
    fi
    cp *.java ${simbox} 2>/dev/null
    chmod a+rw -R ${simbox} 2>/dev/null
fi
#echo $simbox ; exit 1
for t in {1..62}
do
    echo ----------
    timeout_sec=5s
    timeout ${timeout_sec} java -ea Main < ${io}/input.${t} >& ${tmpu}/output.${t}
    exs=$?
    if [ ${exs} -ne 0 ] 
    then
	if [ ${exs} -eq 124 ]
        then
	    echo output.${t} ' (boucle ou trop lent ?)'
	else 
	    echo output.${t} ' (assert or notYetImplemented)'
	    tail ${tmpu}/output.${t}
	    echo tail ${tmpu}/output.${t}
	fi
	exit 1    
    fi
    if ! cat ${io}/output.${t} >& /dev/null
    then
	echo No ${io}/output.${t}
	echo emacs ${tmpu}/output.${t}
	echo cp ${tmpu}/output.${t} ${io}
	exit 1
    fi
    if ! diff ${io}/output.${t} ${tmpu}/output.${t} >& /dev/null
    then
	echo output.${t} ' (wrong result)'
	diff ${io}/output.${t} ${tmpu}/output.${t} | head 
	echo "diff ${io}/output.${t} ${tmpu}/output.${t} | head" 
	exit 1
    fi
    echo output.${t} good
done
echo ----------
echo 'Veeerrryy Goood !'
touch speed.ok
exit 0

