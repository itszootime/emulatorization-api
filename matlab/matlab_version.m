function [ string ] = matlab_version()
    v = ver;
    for i = 1: size(v, 2)
        cur = v(i);
        if strcmp(cur.Name, 'MATLAB')
            string = sprintf('%s Version %s %s', cur.Name, cur.Version, cur.Release);
            return
        end
    end
end

