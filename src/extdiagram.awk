/public|private|protected|^}/ {
    gsub(/\spublic /, "+");
    gsub(/public /, "");
    gsub(/\sprivate /, "-");
    gsub(/\sprotected /, "#");
    gsub(/static/, "{static}");

    # Es un método
    if (/\(.*\)/) {
        gsub(/void /, "");
        match($0, /\(.*\)/, arguments);

        n = split(arguments[0], splitedargs, /\s?,\s?|\(|\)/, seps);

        gsub(/\(.*\)/, swapSignature(n, splitedargs, seps));
        gsub(/\s?{$/, "");
        gsub(/\s?throws.*/, "");
    # Es un atributo
    # TODO: agregar caso de atributo stático
    } else if (/;$/) {
        tomatch = "[#+\\-].*;"
        if (/static/) {
            tomatch = "}\\s.*;"
        }
        match($0, tomatch, arguments);

        n = split(arguments[0], splitedargs, /[#+\-;]|}^\s/, seps);

        gsub(tomatch, swapSignature(n, splitedargs, seps));
    }

    gsub(/;/, "");
    print;
}

function swapSignature(n, splitedargs, seps,     arguments){
    for (i = 2; i < n; i++) {
        if (splitedargs[i] != "") {
            split(splitedargs[i], splitedarg, /\s/);
            splitedargs[i] = splitedarg[2] ": " splitedarg[1];
        }
    }

    arguments = seps[1] splitedargs[2];
    for (i = 3; i < n; i++) {
        arguments = arguments ", " splitedargs[i];
    }

    arguments = arguments seps[n-1];

    return arguments;
}

