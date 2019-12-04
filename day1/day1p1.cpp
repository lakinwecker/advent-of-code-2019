#include <iostream>
#include <sstream>
#include <vector>
#include <iterator>
#include <algorithm>
#include <numeric>

using namespace std;

int fuel(int mass) {
    return mass / 3 - 2;
}

int main() {
    vector<string> lines;
    copy(istream_iterator<string>(cin), istream_iterator<string>(), back_inserter(lines));
    vector<int> masses(lines.size());
    transform(lines.begin(), lines.end(), masses.begin(), [](auto l) {
        istringstream out(l);
        int i;
        out >> i;
        return fuel(i);
    });
    cout << accumulate(masses.begin(), masses.end(), 0) << endl;
}
