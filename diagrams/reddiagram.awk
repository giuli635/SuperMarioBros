/class|interface|enum/ {
    gsub(/public\s/, "");
    split($0, words);
    class = "";
    p = 0;
    i = 0;
    for (s in words) {
        if (words[s] == "extends") {
            p = 1;
            i = 0;
        } else if (words[s] == "implements") {
            p = 2;
            i = 0;
        } else if (words[s] != "{"){
            related[p][i] = words[s];
            i++;
        }
    }

    for (i in related[0]) {
        if (!(related[0][i] ~ /class|abstract|interface/)) {
            name = related[0][i]
        }
    }

    for (i in related[1]) {
        print name " --|> " related[1][i];
    }

    for (i in related[2]) {
        print name " ..|> " related[2][i];
    }

    for (i in related[0]) {
        class = class related[0][i] " "
    }

    split("", related[0])
    split("", related[1])
    split("", related[2])
    print class
}

/(protected|public|private)/ {
    if (!/\(/) {
        split($0, field, /\W/)
        for (i in field) {
            if (field[i] ~ /^[[:upper:]]/) {
                print name " --> " field[i];
            }
        }
    }

    delete field
}

