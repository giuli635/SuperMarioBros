BEGIN {
    print "@startuml";
} 

/class|interface|enum/ {
    sub(/public\s/, "");
    if (/extends|implements/) {
        gsub(/\s(extends|implements).*\{/, "");
        $0 = $0 " {";
    }
    print;
}

/^\s{4}(public|private|protected)/ {
    sub(/\spublic\s/, "+");
    sub(/\sprivate\s/, "-");
    sub(/\sprotected\s/, "#");
    # Es un método
    if (/\(.*\)/) {
        sub(/\s?{$/, "");
        sub(/\s?throws.*/, "");

        match($0, /\(.*\)/, arguments);

        n = split(arguments[0], splitedargs, /\s?,\s?|\(|\)/, seps);

        sub(/\(.*\)/, swapSignature(n, splitedargs, seps));

        if (!(/[#+\-]\w*\(/)) {
            if (/void/) {
                sub(/void /, "");
            } else {
                if (/static/) {
                static = "static"
                } else {
                    static = ""
                }

                match($0, /\s*[#+\-](static\s)?\S*\s/, functionReturn);
                sub(/\s*[#+\-](static\s)?\S*\s/, "", $0);
                match(functionReturn[0], /\S/, symbol);
                sub(/\s*[#+\-](static\s)?/, "", functionReturn[0]);

                $0 = "   " symbol[0] static $0 ": " functionReturn[0];
            }
        }
    # Es un atributo
    } else if (/;$/) {
        if (/static/) {
            match($0, /static\s.*;/, arguments);
            n = split(arguments[0], splitedargs, /[#+\-;]|static\s/, seps);
            gsub(/static\s.*;/, swapSignature(n, splitedargs, seps));
        } else {
            match($0, /[#+\-].*;/, arguments);
            n = split(arguments[0], splitedargs, /[#+\-;]|}^\s/, seps);
            gsub(/[#+\-].*;/, swapSignature(n, splitedargs, seps));
        }
    }
    sub(/static/, "{static}");
    sub(/;/, "");

    print;
}

/^}/ {
    print;
    print "";
}

function swapSignature(n, splitedargs, seps,     arguments){
    for (i = 2; i < n; i++) {
        if (splitedargs[i] != "") {
            split(splitedargs[i], splitedarg, /[^,]\s/, intraSeps);
            splitedargs[i] = splitedarg[2] substr(intraSeps[2], 0, 1) ": " splitedarg[1] substr(intraSeps[1], 0, 1);
        }
    }

    arguments = seps[1] splitedargs[2];
    for (i = 3; i < n; i++) {
        arguments = arguments ", " splitedargs[i];
    }

    arguments = arguments seps[n-1];

    return arguments;
}

END {
    print "@enduml";
} 
